# AvtarView

[![](https://jitpack.io/v/anoop44/AvtarView.svg)](https://jitpack.io/#anoop44/AvtarView) [![License](http://img.shields.io/badge/license-MIT-green.svg?style=flat)]()

Easy to use highly customizable View for showing Avtars for names and contacts

AvtarView is built on top of ImageView, so incase if you want to show images for some items in a list and Avtar for others, no need to have two views. With the AvtarView you can show images as well as Avtar.

![](https://github.com/anoop44/AvtarView/blob/master/screenshots/screenshot1.png?raw=true)

## Adding in your project
Add to your root build.gradle:
```Groovy
allprojects {
	repositories {
	    // ...
	    maven { url "https://jitpack.io" }
	}
}
```

Add the dependency:
```Groovy
dependencies {
    compile 'com.github.anoop44:AvtarView:1.0.0'
}
```

## Usage

### AvtarView
```xml
	<ss.anoop.avtarview.AvtarView
		android:id="@+id/avtarView"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		app:text="Avtar"
		app:textColor="@android:color/white"
		app:strokeWidth="2dp"
		app:strokeColor="@android:color/white"
		/>
```

`AvtarView` can be modified at runtime also, setter methods are available for all the attributes
```java
    avtarView.setText(text);
    avtarView.setTextColor(Color.WHITE);
    avtarView.setStrokeWidth(2);
    avtarView.setStrokeColor(Color.WHITE);
```

### Image

If you want to show image instead of avtars, use it as an `ImageView`. `android:src` in XML and `avtarView.setImageResource` works as normal. When you set an image resource or drawable, properties otherthan `strokeWidth` and `strokeColor` will be ignored


## Contribution

I know there is lot of room for improvement in terms of feature and the code. so feel free to suggest your ideas about it in the issues,
or, if you found a bug and you have some free time to fix it, writing a few lines of code in __Kotlin__,
feel free to send me PRs.

## License

    MIT License

    Copyright (c) 2016 Alexey Derbyshev

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.