# Biometric Authentication Demo Project

This is a demo project showcasing the implementation of Android Biometric Authentication using Jetpack Compose.

## Overview

Biometric authentication provides a secure and convenient way for users to access applications using their biometric data such as fingerprint, face, or iris recognition. This project demonstrates the integration of biometric authentication into an Android application using Jetpack Compose, making the authentication process seamless and user-friendly.

## Features

- Utilizes the latest Android Biometric APIs
- Supports both strong biometric authentication and device credential fallback
- Handles various authentication scenarios and error conditions gracefully
- Provides a clean and intuitive user interface built with Jetpack Compose

## Prerequisites

- Android Studio Arctic Fox (2020.3.1) or higher
- Android SDK 30 or above
- Kotlin 1.5.0 or above

## Getting Started

1. Clone the repository to your local machine:

   ```
   git clone https://github.com/Hasnain17/BioMetricAuth.git
   ```

2. Open the project in Android Studio.

3. Build and run the project on an Android device or emulator.

## Usage

1. Launch the application on your device.

2. Tap on the "Authenticate" button to initiate the biometric authentication process.

3. Follow the on-screen prompts to authenticate using your enrolled biometric data.

4. Depending on the device and authentication status, you may encounter various outcomes such as successful authentication, authentication failure, or prompts to set up biometric authentication if not already configured.


##Flow 

The application displays a button labeled "Authenticate". Clicking the button triggers the biometric prompt with a title of "Sample Prompt" and description of "Sample prompt description".

The app handles different biometric results and displays informative messages accordingly:


1. Authentication Success: "Authentication Success"

2. Authentication Failed: "Authentication Failed"

3. Authentication Error: Displays the specific error message.

4. Authentication Not Set: If biometrics are not enrolled on the device (Android versions below 30), it displays "Authentication not set". On Android 30+, it guides the user to the system settings for enrolling biometrics.

5. Hardware Unavailable: "Hardware Unavailable" (e.g., no fingerprint sensor or face recognition camera).

6. Feature Unavailable: "Feature Unavailable" (Biometric authentication is not supported on the device).


##Dependencies

1. AndroidX Biometric library

2. Jetpack Compose
 


## Screenshots

![image](https://github.com/Hasnain17/BioMetricAuth/assets/62245237/d7a7562e-7ad2-4fb7-ab68-ca74d6eb128e)


## Contributing

Contributions are welcome! If you'd like to contribute to this project, please fork the repository and submit a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

[Android Source](https://source.android.com/docs/security/features/biometric)

[Philipp Lackner](https://www.youtube.com/@PhilippLackner)


