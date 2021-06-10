# Overview

<div align="center">

[![Build Status](https://api.travis-ci.com/astrapi69/checksum-up.svg?branch=develop)](https://travis-ci.com/github/astrapi69/checksum-up)
[![Coverage Status](https://codecov.io/gh/astrapi69/checksum-up/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/checksum-up)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/checksum-up.svg?style=flat)](https://github.com/astrapi69/checksum-up/issues)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/checksum-up/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/checksum-up)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/checksum-up.svg)](http://www.javadoc.io/doc/io.github.astrapi69/checksum-up)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

</div>

Utility library that provides utility classes for calculate checksums for files, byte arrays and
objects

If you like this project put a ⭐ and donate

# Donations

This project is kept as an open source product and relies on contributions to remain being
developed. If you like this library, please consider a donation

over paypal: <br><br>
<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=MJ7V43GU2H386" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" style="border: none" />
</a>
<br><br>
or over bitcoin(BTC) with this address:

bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy.png"
alt="Donation Bitcoin Wallet" width="250"/>

or over FIO with this address:

FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop.png"
alt="Donation FIO Wallet" width="250"/>

or over Ethereum(ETH) with:

0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D.png"
alt="Donation Ethereum Wallet" width="250"/>

or over Ethereum Classic(ETC) with:

0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD.png"
alt="Donation Ethereum Classic Wallet" width="250"/>

or over Dogecoin(DOGE) with:

D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1.png"
alt="Donation Dogecoin Wallet" width="250"/>

or over Monero(XMR) with:

49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw

<img
src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw.png"
alt="Donation Monero Wallet" width="250"/>

or over flattr:

<a href="http://flattr.com/thing/4067696/astrapi69checksum-up-on-GitHub" target="_blank">
<img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0" />
</a>

## Note

No animals were harmed in the making of this library.

## License

The source code comes under the liberal MIT License, making checksum-up great for all types of applications.

## Maven dependency

Maven dependency is now on sonatype. Check
out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~checksum-up~~~)
for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core
functionality of checksum-up:

Than you can add the dependency to your dependencies:

	<properties>
			...
		<!-- CHECKSUM-UP VERSION -->
		<checksum-up.version>1.2</checksum-up.version>
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

## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of checksum-up:

```
define version in file gradle.properties

checksumUpVersion=1.2
```

or in build.gradle ext area

```
ext {
			...
    checksumUpVersion = '1.2'
			...
}
```

and than add the dependency to the dependencies area

```
dependencies {
			...
implementation("io.github.astrapi69:checksum-up:$checksumUpVersion")
			...
}
	
```

## Semantic Versioning

The versions of checksum-up are maintained with the Simplified Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning for this project you can visit this [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Simplified-Semantic-Versioning).

## Want to Help and improve it? ###

The source code for checksum-up are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/checksum-up/fork](https://github.com/astrapi69/checksum-up/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/checksum-up/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the checksum-up developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/checksum-up/issues).

## Credits

|**Travis CI**|
|     :---:      |
|[![Travis CI](https://travis-ci.com/images/logos/TravisCI-Full-Color.png)](https://travis-ci.com/github/astrapi69/checksum-up)|
|Special thanks to [Travis CI](https://travis-ci.org) for providing a free continuous integration service for open source projects|
|     <img width=1000/>     |

|**Nexus Sonatype repositories**|
|     :---:      |
|[![sonatype repository](https://img.shields.io/nexus/r/https/oss.sonatype.org/io.github.astrapi69/checksum-up.svg?style=for-the-badge)](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~checksum-up~~~)|
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
