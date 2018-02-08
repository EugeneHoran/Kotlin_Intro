# Kotlin_Intro
First Kotlin project.

Note: I am leaving out DI & Rx to learn the core fundementals. Once the project is complete I will add DI &Rx as needed. 

## Goal 
- Get a solid understanding of Kotlin (note: so far it is amazing and I wish I converted earlier)
- Using Kotlin and MVVM archecture to build a News application retreiving RSS feeds from news sites.  
- Unit Test in Kotlin

Kotlin Stages:
- ~~Retrofit, Http3, and custom LiveLib (Will be converting this to Kotlin as well) for Network connections..~~
  - Unit Test with Kotlin
- ~~Room database~~
  - Unit Test with Kotlin
- ~~UI and Data Handling (data binding with Arch Components)~~
  - ~~ViewModels, Fragmentation, RecyclerAdapters...~~


<p float="top">
<img src="https://github.com/EugeneHoran/Kotlin_Intro/blob/master/images/device-2018-02-08-184335.png" width="220" />
<img src="https://github.com/EugeneHoran/Kotlin_Intro/blob/master/images/device-2018-02-08-184356.png" width="220" />
<img src="https://github.com/EugeneHoran/Kotlin_Intro/blob/master/images/device-2018-02-08-184412.png" width="220" />
</p>

### Libraries

```
ext.gradle_version = '3.0.1'
ext.kotlin_version = '1.2.21'
ext.support_version = '27.0.2'
ext.arch_version = '1.1.0'
ext.room_version = '1.1.0-alpha1'
ext.retro_version = '2.3.0'
ext.http3_version = '3.9.0'
ext.glide_version = '4.5.0'

dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
    // android/support
    implementation "com.android.support:appcompat-v7:$support_version"
    implementation "com.android.support:design:$support_version"
    implementation "com.android.support:recyclerview-v7:$support_version"
    implementation "com.android.support:cardview-v7:$support_version"
    implementation "com.android.support:customtabs:$support_version"
    implementation "com.android.support:support-vector-drawable:$support_version"
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    // Kotlin
    kapt "com.android.databinding:compiler:$gradle_version"
    // Arch ViewModel, LiveData and Room
    implementation "android.arch.lifecycle:extensions:$arch_version"
    annotationProcessor "android.arch.lifecycle:compiler:$arch_version"
    implementation "android.arch.persistence.room:runtime:$room_version"
    kapt "android.arch.persistence.room:compiler:$room_version"
    // Retro
    implementation "com.squareup.retrofit2:retrofit:$retro_version"
    implementation "com.squareup.retrofit2:converter-gson:$retro_version"
    implementation "com.squareup.okhttp3:logging-interceptor:$http3_version"
    // Glide
    implementation "com.github.bumptech.glide:glide:$glide_version"
    annotationProcessor "com.github.bumptech.glide:compiler:$glide_version"
    // Live Lib
    implementation project(':livelib')

    // TEST
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}

```
