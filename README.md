# Overview

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/checksum-up/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/checksum-up/actions/workflows/gradle.yml)
[![Coverage Status](https://codecov.io/gh/astrapi69/checksum-up/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/checksum-up)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/checksum-up.svg?style=flat)](https://github.com/astrapi69/checksum-up/issues)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/checksum-up)](https://central.sonatype.com/artifact/io.github.astrapi69/checksum-up)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/checksum-up.svg)](http://www.javadoc.io/doc/io.github.astrapi69/checksum-up)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
[![Hits Of Code](https://hitsofcode.com/github/astrapi69/checksum-up?branch=develop)](https://hitsofcode.com/github/astrapi69/checksum-up/view?branch=develop)

</div>

Utility library that provides extension classes that provides the following features:

calculate checksums for:

* byte arrays
* byte object arrays
* files and directories
* string and java objects

> Please support this project by simply putting a Github <!-- Place this tag where you want the button to render. -->
<a class="github-button" href="https://github.com/astrapi69/checksum-up" data-icon="octicon-star" aria-label="Star astrapi69/checksum-up on GitHub">Star ⭐</a>
>
> Share this library with friends on Twitter and everywhere else you can
>
> If you love this project [![donation](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

## gradle dependency

Replace the variable ${latestVersion} with the current latest version: [![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/checksum-up)](https://central.sonatype.com/artifact/io.github.astrapi69/checksum-up)

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of checksum-up:

define version in file gradle.properties
```
checksumUpVersion=${latestVersion}
```

or in build.gradle ext area

```
    checksumUpVersion = '${latestVersion}'
```

and than add the dependency to the dependencies area

```
implementation("io.github.astrapi69:checksum-up:$checksumUpVersion")
```

## Maven dependency

The library is on Maven Central. Check out [Maven Central](https://central.sonatype.com/artifact/io.github.astrapi69/checksum-up) for the latest release; snapshots
are described below.

Add the following maven dependency to your project `pom.xml` if you want to import the core
functionality of checksum-up:

Than you can add the dependency to your dependencies:

    <properties>
            ...
        <!-- CHECKSUM-UP VERSION -->
        <checksum-up.version>${latestVersion}</checksum-up.version>
            ...
    </properties>
            ...
        <dependencies>
            ...
            <!-- CHECKSUM-UP DEPENDENCY -->
            <dependency>
                <groupId>io.github.astrapi69</groupId>
                <artifactId>checksum-up</artifactId>
                <version>${checksum-up.version}</version>
            </dependency>
            ...
        </dependencies>

## 📸 Snapshots

[![Snapshot](https://img.shields.io/badge/dynamic/xml?url=https://central.sonatype.com/repository/maven-snapshots/io/github/astrapi69/checksum-up/maven-metadata.xml&label=snapshot&color=red&query=.//versioning/latest)](https://central.sonatype.com/repository/maven-snapshots/io/github/astrapi69/checksum-up/maven-metadata.xml)

This section describes how to import snapshot versions into your project.
Add the following code snippet to your gradle file in the repositories section:
```
repositories {
   //...
```
```groovy
    maven {
        name "Central Portal Snapshots"
        url "https://central.sonatype.com/repository/maven-snapshots/"
        mavenContent {
            snapshotsOnly()
        }
    }
```
```
}
```


## Support this project

If checksum-up is useful to you, a star on [GitHub](https://github.com/astrapi69/checksum-up) helps others find it.
Sharing it or reporting a bug helps just as much.

If you would like to contribute financially, the channels are on the repository's Sponsor button, which
reads [.github/FUNDING.yml](.github/FUNDING.yml): GitHub Sponsors, Liberapay, Ko-fi and PayPal.

## Note

No animals were harmed in the making of this library.

## License

The source code comes under the liberal MIT License, making checksum-up great for all types of applications.

## Semantic Versioning

The versions of checksum-up are maintained with the Simplified Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning for this project you can visit this [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Simplified-Semantic-Versioning).

## Verifying the release signatures

The releases on Maven Central are signed with the OpenPGP key

```text
5B2F A6B1 1E29 8BC0 9287  F423 D8C4 0351 8C49 CA75
```

Releases after 3.2 are signed by its signing subkey

```text
6D67 F844 2A6F CC96 BD7C  1B5E 9FCF 7C97 10E2 BD8D
```

valid until 2028-10-09; 3.2 and earlier were signed by the primary key itself. The key, with the subkey, is on keyserver.ubuntu.com and keys.openpgp.org.

To check a download, fetch the key once, then verify the signature that lies next to each file on
Maven Central:

```sh
gpg --keyserver hkps://keys.openpgp.org --recv-keys 5B2FA6B11E298BC09287F423D8C403518C49CA75
gpg --verify checksum-up-3.2.jar.asc checksum-up-3.2.jar
```

gpg answers `Good signature` and prints the `Primary key fingerprint`, for a release signed by the
subkey also the `Subkey fingerprint`. Both have to be the ones above. The warning that the key "is
not certified with a trusted signature" only says that you have not marked the key as trusted in your
own keyring; the fingerprints are what to compare.

## Want to Help and improve it? ###

The source code for checksum-up are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/checksum-up/fork](https://github.com/astrapi69/checksum-up/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/checksum-up/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the checksum-up developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/checksum-up/issues).

## Credits

|**Maven Central**|
|     :---:      |
|[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/checksum-up?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.astrapi69/checksum-up)|
|Special thanks to [sonatype repository](https://www.sonatype.com) for providing a free maven repository service for open source projects|
|     <img width=1000/>     |

|**codecov.io**|
|     :---:      |
|[![Coverage Status](https://codecov.io/gh/astrapi69/checksum-up/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/checksum-up)|
|Special thanks to [codecov.io](https://codecov.io) for providing a free code coverage for open source projects|
|     <img width=1000/>     |

|**javadoc.io**|
|     :---:      |
|[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/checksum-up.svg)](http://www.javadoc.io/doc/io.github.astrapi69/checksum-up)|
|Special thanks to [javadoc.io](http://www.javadoc.io) for providing a free javadoc documentation for open source projects|
|     <img width=1000/>     |

## Git hooks

The repository's git hooks live in `.githooks`: `commit-msg` where present, and `pre-commit`, which refuses a
commit whose files Spotless would format. Activate them once per clone or worktree:

```bash
git config core.hooksPath .githooks
```
