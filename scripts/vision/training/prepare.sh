#!/bin/bash

VIDEOS=videos
G=[^0-9]*\([0-9]*\)
T=[^0-9]*
TS=`date +'%s'` #get unix epoch timestamp for generating unique file names

prepare_each_dir () {
	rm -f labels.txt

	#remove first 18 lines, then remove the last 2 lines by flipping the file, listing lines staring with 3rd, then flipping back
	< *.xml tail -n +19 | tail -r | tail -n +3 | tail -r |
	#match number groups preceded by non-number characters, and print only 2nd, 3rd, 8th and 9th groups since those are the 
	#pixel coordinates we need (top left and bottom right)
	sed -E "s/$G$G$G$G$G$G$G$G$G$G$G$G$G$T/car 0.0 0 0 \2.0 \3.0 \8.0 \9.0 0 0 0 0 0 0 0/"\
	> labels.txt
	#split the labels into a file per line, with a prefix and line number in the file name
	awk -v ts=$TS '{filename = ts "_" NR-1".txt"; print >> filename; close(filename)}' labels.txt
	rm -f labels.txt
	#unzip images
	unzip -q *.zip
	for filename in *.jpg; do mv "$filename" "${TS}_$filename"; done;
	
	mv *0.jpg ../../set/val/images
	mv *0.txt ../../set/val/labels
	mv *.jpg ../../set/train/images
	mv *.txt ../../set/train/labels
}

rm -rf set
mkdir set set/val set/train set/val/images set/val/labels set/train/images set/train/labels
cd $VIDEOS
for d in *
do
	echo "Processing" $d
    ( cd $d && prepare_each_dir )
done
cd ..
zip -qr set.zip set
rm -rf set/train/
mv set/val/images/*.* set/val
mv set/val/labels/*.* set/val
rm -rf set/val/images
rm -rf set/val/labels

echo "Training set ready in set.zip. Validate a few images in set/val using Fiji."