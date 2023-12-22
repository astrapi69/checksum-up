## Change log
----------------------

Version 3
-------------

CHANGED:

- update to jdk version 17
- update gradle to new version 8.5
- update of gradle-plugin dependency 'com.github.ben-manes.versions.gradle.plugin' to new version 0.50.0
- update of gradle-plugin dependency 'org.ajoberstar.grgit:grgit-gradle' to new minor version 5.2.1
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new minor version 6.23.3
- update of dependency crypt-api to new version to 8.7
- update of test dependency file-worker to new minor version 17.1
- update of test dependency test-objects to new minor version 8.2
- update of test dependency 'com.github.meanbeanlib:meanbean' to new version 3.0.0-M9
- update of test dependency testng to new patch version 7.8.0


Version 2.2
-------------

ADDED:

- new method created for get checksum from an array of byte arrays
- new method created for get checksum from a directory
- new extension class StringChecksumExtensions

CHANGED:

- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new minor version 6.11.0
- update of gradle-plugin dependency 'com.github.ben-manes.versions.gradle.plugin' to new version 0.43.0
- update of test dependency file-worker to new minor version 11.5
- update of test dependency test-object to new minor version 7.2

Version 2.1
-------------

ADDED:

- new method in ObjectChecksumExtensions that creates the checksum of two different serialized objects
- new method in ObjectChecksumExtensions that creates the checksum of several serialized objects of the same type
- new method in ObjectChecksumExtensions that creates a new byte array from several serialized objects of the same type

CHANGED:

- update gradle to new version 7.5.1
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new minor version 6.10.0
- update of dependency crypt-api to new version to 8.3
- update of test dependency test-object to new minor version 7.1
- update of test dependency file-worker to new minor version 11.1
- removed interface ChecksumAlgorithm from this module that is now in the new version of the module crypt-api

Version 2
-------------

ADDED:

- new method in ByteArrayChecksumExtensions that resolves the hexadecimal string from CRC32 checksum
- new method in ByteArrayChecksumExtensions that resolves the hexadecimal string from ADLER32 checksum
- new method in FileChecksumExtensions that resolves the hexadecimal string from CRC32 checksum
- new method in FileChecksumExtensions that resolves the hexadecimal string from ADLER32 checksum
- new method in ObjectChecksumExtensions that resolves the hexadecimal string from CRC32 checksum
- new method in ObjectChecksumExtensions that resolves the hexadecimal string from ADLER32 checksum
- new test dependency file-worker in minor version 8.2

CHANGED:

- update to jdk version 11
- update gradle to new version 7.5
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new minor version 6.9.0
- update of gradle-plugin dependency 'org.ajoberstar.grgit:grgit-gradle' to new minor version 5.0.0
- update of dependency crypt-api to new version to 8.1
- update of test dependency test-objects to new minor version 6.1
- update of test dependency testng to new patch version 7.6.1

Version 1.3
-------------

CHANGED:

- update gradle to new version 7.4.2
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.42.0
- added github action for gradle ci build
- update of testng test dependency version to 7.5
- update of test-objects test dependency  to new major version 6

Version 1.2
-------------

CHANGED:

- changed to new package io.github.astrapi69

Version 1.1
-------------

ADDED:

- new ChecksumExtensions class for resolve the checksum algorithm
- new project properties

CHANGED:

- update gradle to new version 6.4.1

Version 1
-------------

ADDED:

- this changelog file
- created PULL_REQUEST_TEMPLATE.md file
- created CODE_OF_CONDUCT.md file
- created CONTRIBUTING.md file
- provide package.html for the javadoc of packages
- moved classes from mystic-crypt project
