# JioLocalizationSDK_ANDROID


## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Prerequisites](#prerequisites)
4. [Project Settings](#project-settings)
5. [Integration Steps](#integrate-sdk)
    - [Add Permissions](#add-permissions-for-network-and-device-access)
    - [Configure SDK](#configuration)
    - [Load Localization](#load-localization)
6. [Usage Examples](#usage-example)
7. [Demo App]()
6. [Trobleshooting](#troubleshooting)

## Introduction
The JioLocalizationSDKAndroid offers a robust solution for managing localization in your Android applications. With this SDK, you can effortlessly fetch localization data, retrieve localized strings, and handle dynamic placeholders, ensuring your app can cater to a global audience with ease.


---

## Features

1. Fetch localization data.
2. Retrieve localized strings with dynamic placeholders.
3. Support for nested JSON structures.
4. Handle missing localization keys with fallback values.



## Prerequisites

Before getting started with this example app, please ensure you have the following software installed on your machine:

- Android Studio
- Support for Java 11

## Project Settings

## Configure JioMeet Localiszation SDK inside your app

i.: Generate a Personal Access Token for GitHub

- Settings -> Developer Settings -> Personal Access Tokens -> Generate new token
- Make sure you select the following scopes (“ read:packages”) and Generate a token
- After Generating make sure to copy your new personal access token. You cannot see it again! The only option is to generate a new key.

ii. Update build.gradle inside the application module

```kotlin
    repositories {
    maven {
        credentials {
            <!--github user name-->
                username = ""
            <!--github user token-->
                password = ""
        }
        url = uri("https://maven.pkg.github.com/JioMeet/JioLocalizationSDK_Android")
    }
    google()
    mavenCentral()
}
```

iii. In Gradle Scripts/build.gradle (Module: <projectname>) add the SDK dependency. The dependencies
section should look like the following:

```gradle
dependencies {
    ...
    implementation " com.jio.localization.jiolocalizationsdk:<version>"
    ...
}
```

Find the [Latest version](https://github.com/JioMeet/JioLocalizationSDK_Android/releases) of the Core
SDK and replace <version> with the one you want to use. For example: 1.0.0-SNAPSHOT.

### Add permissions for network and device access.

In /app/Manifests/AndroidManifest.xml, add the following permissions after </application>:

```gradle
 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### Integrate SDK

Create and configure the instance of `JioLocalization`. 

### Configuration

The `JioLocalization` instance can be configured with the following options:

- `context`: The application context.
- `baseURL`: The base URL of the localization service.
- `apiKey`: The API key for the localization service.

The `JioLocalization` instance is created using the `JioLocalization.Builder` class.

```kotlin
    val builder = JioLocalization.Builder()
    builder.apply {
        init(context, Server.SIT.baseURL)
        setApiKey(apiKey)
    }
    jioLocalization = builder.build()
    
```

### Load Localization
This method fetches localization data for a specified language code from the remote service. It is designed to be called during the initialization phase of your application to ensure that all user-facing strings are appropriately localized based on the user’s language preference. The method handles both success and error cases, allowing for seamless user experience while providing feedback on the localization loading process

```kotlin
  jioLocalization.loadLocalization("en"){ result ->
        when (result) {
            is Completion.Success -> {
                Log.d("TAG", "Localization successfully loaded: ", )
            }
            is Completion.Error -> {
                Log.e("TAG", "Some error while loading localization: ")
            }
        }
    }
```



### Usage Example

### getLocalizedString
This method retrieves localized strings based on a provided key. It supports loading a key with a default value and can also handle dynamic value placeholders within the string.

`Basic Retrieval:` The first example retrieves the localized string for the key "callDeclined". If the key does not exist in the localization data, it will return null or the default value if specified.
``` kotlin
// Retrieve a localized message without a default value
val message = viewModel.getLocalizedString("callDeclined")
```

`With Default Value:` The second example demonstrates how to use a default value. If the key "callDeclined" is not found, the method will return "Default Value" instead.

``` kotlin
// Retrieve a localized message without a default value
val messageWithDefault = viewModel.getLocalizedString("callDeclined", "Default Value")
```

`Dynamic Values:` The third example shows how to load a key that contains a dynamic value placeholder (%@). In this case, the placeholder will be replaced with the string "English" in the final output.
``` kotlin
val dynamicMessage = viewModel.getLocalizedString("callDeclined", mapOf("%@" to "English"), "Default Value")
```


**Note**:
1. Ensure that the localization data is loaded before calling this method to prevent potential null returns.
2. Use dynamic value placeholders to create more flexible and context-aware localized messages


### Demo App
The JioLocalizationSDK_Android demonstrates how to integrate the jiolocalizationsdk into your Android project. The app showcases various examples of fetching localized strings, including simple strings, nested strings, and strings with dynamic placeholders


### TroubleShooting
Facing any issues while integrating or installing the JioLocalization Android SDK kit, please connect with us via https://translate.jio/contact-us.html
