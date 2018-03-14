export DISPLAY=:0
CLIENT_IP=laghai.local

gst-launch-1.0 nvcamerasrc /dev/video1 ! omxh265enc ! rtph265pay ! udpsink host=$CLIENT_IP port=5000
