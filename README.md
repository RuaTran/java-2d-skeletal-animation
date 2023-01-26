
# Java 2D Skeletal Animation library
Created as a personal project to explore the challenges of implementing 2D skeletal animation similar to [Spine2D](http://esotericsoftware.com/spine-demos). The art assets utilized are from the game Ragnarok Battle Offline, which is owned by フランスパン, otherwise all code is owned by me. It is very barebones, and I would've like to include a clean editor, but Java development has very robust open-source tools for this already. The benefit of this tool is explained a bit below, but the gist of it is that this entire project's executable is only ~2MB!

The source-code includes self-written game engine that demonstrates the usage of the library, which is to take individual small images and mesh them together to make animations. For example: the following spritesheet (and another including the face components)...

![preview](https://user-images.githubusercontent.com/110074141/214950221-67784245-e299-4cae-94b9-68f3682c2964.png)

... is used to create the following animations. In the example below, animations are playing back in the top left corner, while I am controlling the character and the pet character with the arrow keys.
![preview](https://user-images.githubusercontent.com/110074141/214951140-1c9a56f0-088c-4423-a1b8-babe3afac0f8.gif)
The benefit of this is that art assets have high reusability with extraordinarily low impact to filesize as the number of animations goes up, as the animations aren't full images. They are simply keyframes represented by numbers with a specified scale, rotation, and skew about an axis. Another benefit is that animations can be played back at any framerate and they will still be buttery smooth! The trade-off is that performing these calculations on the images occurs at runtime. Time complexity in exchange for space complexity!

## Usage
Click on **Releases** on the right side of the site to download v1.0.0, which contains a zip file with the executable. Also, the Java **source code** is available for you to inspect and modify freely. In order to build it, download the source and open the project in your desired editor and then compile.
## Contact
Please feel free to send a message to MunfMunf#9104 if you're looking at the code in an attempt to modify it or use bits of it to make your own project. I love learning, and I'm always happy to help people learn. So please send me a message if you'd like to understand this code, or incorporate it into your own projects!

## Disclaimer
Ragnarok Battle Offline is owned by フランスパン and thus the art assets are owned by them. If it is necessary then I can take this project down, or replace the existing art assets with my own.
