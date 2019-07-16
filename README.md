# KSnack

[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![](https://jitpack.io/v/onurkagan/ksnack.svg)](https://jitpack.io/#onurkagan/ksnack)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-KSnack-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7533)


You can create customized Snackbar with KSnack. KSnack has 2 view type; KSnack and Minimal KSnack.

### Minimal KSnack

![](https://raw.githubusercontent.com/onurkagan/KSnack/master/app/src/main/res/drawable/minimal_ksnack.png)

1. Initialize Minimal KSnack.
```java
MinimalKSnack minimalKSnack = new MinimalKSnack(MainActivity.this);
```
2. Set Features. Styles;  *`STYLE_DEFAULT`, 
                          `STYLE_INFO`, 
                          `STYLE_SUCCESS`, 
                          `STYLE_ERROR`, 
                          `STYLE_WARNING`*
```java
minimalKSnack
    .setMessage("This is minimal KSnack !") // message
    .setStyle(MinimalKSnackStyle.STYLE_SUCCESS) // style
    .setBackgroundColor(R.color.colorGray) // background color
    .setBackgrounDrawable(R.drawable.background_ex_one) // background drawable
    .setAnimation(Fade.In.getAnimation(), Fade.Out.getAnimation()) // show and hide animations
    .setDuration(4000) // you can use for auto close.
    .show(); 
```
3. Close Minimal KSnack.
```java
minimalKSnack.dismiss();
```

### KSnack

![](https://raw.githubusercontent.com/onurkagan/KSnack/master/app/src/main/res/drawable/ksnack.png)

1. Initialize KSnack.
```java
KSnack kSnack = new KSnack(MainActivity.this);
```
2. Set Features. 
```java
kSnack
  .setListener(new KSnackBarEventListener() { // listener
      @Override
      public void showedSnackBar() {
          System.out.println("Showed");
      }

      @Override
      public void stoppedSnackBar() {
          System.out.println("Stopped");
      }
  })
  .setAction("Text", new View.OnClickListener() { // name and clicklistener
      @Override
      public void onClick(View v) {
          System.out.println("Your action !");
      }
  })
  .setMessage("Your message.") // message
  .setTextColor(R.color.white) // message text color
  .setBackColor(R.color.colorGray) // background color
  .setButtonTextColor(R.color.white) // action button text color
  .setBackgrounDrawable(R.drawable.background_ex_one) // background drawable
  .setAnimation(Slide.Up.getAnimation(kSnack.getSnackView()), Slide.Down.getAnimation(kSnack.getSnackView()))
  .setDuration(4000) // you can use for auto close.
  .show(); 
```
3. Close Minimal KSnack.
```java
kSnack.dismiss();
```

### Animations

  #### 1. Fade
  ```java
 kSnack.setAnimation(Fade.In.getAnimation(), Fade.Out.getAnimation()) // show and hide animations
  ```
  
  #### 2. Slide
  ```java
 minimalKSnack.setAnimation(Slide.Up.getAnimation(minimalKSnack.getSnackView()), Slide.Down.getAnimation(minimalKSnack.getSnackView()))   
  ```
  Slide animations need KSnack or MinimalKSnack view. You should use ```getSnackView()``` or ```getMinimalSnackView()``` functions for correct view object.

### Installation
Step 1. Add the JitPack repository to your build file. 
```gradle
allprojects {
    repositories {
      maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency.
```gradle
dependencies {
  implementation 'com.github.onurkagan:KSnack:1.0.2'
}
```
