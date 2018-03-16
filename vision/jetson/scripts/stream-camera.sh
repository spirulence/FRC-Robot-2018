export DISPLAY=:0
CLIENT_IP=laghai.local

gst-launch-1.0 nvcamerasrc ! 'video/x-raw(memory:NVMM), width=(int)1920, height=(int)1080'! omxh265enc ! rtph265pay ! udpsink host=$CLIENT_IP port=5000
