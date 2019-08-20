# Downloader

--------------------------------------------------------------------
Useful library for downloading file, mp3, mp4, ..... any file

Options
- pause, resume, cancel, queue 
- multi download
- add header request
- set download directory
- set time out connection
- show progress download (percent, downloaded size, total size)
- event every action (onStart, onPause, onResume, onProgress, onComplete, onFailure, onCancel)

### Issue
- [bug report](.github/ISSUE_TEMPLATE/bug_report.md)
- [feature request](.github/ISSUE_TEMPLATE/feature_request.md)


### Usage

add to root build.gradle
```groovy

    maven { url "https://jitpack.io" }

```

add to module build.gradle
```groovy

    implementation 'com.github.alirezat775:downloader:{latest-version}'

```

<img src="https://raw.githubusercontent.com/alirezat775/downloader/master/assets/demo.gif" width="200" height="400" />


- [changelog](CHANGELOG)

### contribution guidelines

please commit in development branch and send a pull request to merge development branch.
explain better in a commit message, don't remove class author and add your data in a header of class
