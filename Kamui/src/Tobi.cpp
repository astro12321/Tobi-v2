#include "Tobi_Tobi.h"

#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <linux/if.h>
#include <linux/if_tun.h>
#include <stdio.h>
#include <sstream>
#include <iomanip>
#include <list>
#include <vector>

#define BUFFERSIZE 4096


int fdTun, bytesRead;
unsigned char buffer[BUFFERSIZE];
char devname[] = "tun0";
std::list<std::string> mylist;


int opentun(char *devname, int *fd)
{
    struct ifreq ifr;
    int err;

    if ((*fd = open("/dev/net/tun", O_RDWR)) == -1) { perror("Error opening /dev/net/tun"); return -1; }

    memset(&ifr, 0, sizeof(ifr));
    ifr.ifr_flags = IFF_TUN | IFF_NO_PI;
    strncpy(ifr.ifr_name, devname, IFNAMSIZ); 

    if ((err = ioctl(*fd, TUNSETIFF, (void *) &ifr)) == -1) { perror("ioctl TUNSETIFF"); close(*fd); return -1; }

    return 0;
}


std::string bytesToHex(unsigned char *bytes, int len)
{
    std::stringstream ss;
    ss << std::hex;

    for (int i = 0; i < len; ++i)
        ss << std::setw(2) << std::setfill('0') << (int)bytes[i];

    return ss.str();
}


std::vector<unsigned char> stringToHex(std::string str)
{
    std::stringstream ss;
    std::vector<unsigned char> hexVec;
    int buffer;
    size_t offset = 0;

    while (offset < str.length()) 
    {
        ss.clear();
        ss << std::hex << str.substr(offset, 2);
        ss >> buffer;

        hexVec.push_back(static_cast<unsigned char>(buffer));

        offset += 2;
    }

    return hexVec;
}


JNIEXPORT void JNICALL Java_Tobi_Tobi_kamui(JNIEnv *env, jobject thisObject)
{
    opentun(devname, &fdTun);

    while (true) 
    {
        bytesRead = read(fdTun, buffer, sizeof(buffer)); //Read TUN interface

        if (bytesRead < 0) perror("Error reading from tun interface");

        std::string pkt = bytesToHex(buffer, bytesRead);
        
        mylist.push_back(pkt);
    }
}


JNIEXPORT void JNICALL Java_Tobi_Tobi_sendPkt(JNIEnv *env, jobject thisObject, jstring jniPkt) 
{
    const char *buffer = env->GetStringUTFChars(jniPkt, NULL);

    std::string pkt(buffer);
    
    std::vector<unsigned char> vecBuffer = stringToHex(pkt);
    int len = pkt.length() / 2;

    if(write(fdTun, &vecBuffer[0], len) < 0) perror("Error writing to tun interface");
}


JNIEXPORT jstring JNICALL Java_Tobi_Tobi_getPkt(JNIEnv *env, jobject thisObject) 
{
    std::string result = ".";

    if(!mylist.empty()) {
        result = mylist.front();
        mylist.pop_front();
    }
    
    return env->NewStringUTF(result.c_str());
}