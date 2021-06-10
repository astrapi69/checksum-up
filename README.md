# checksum-up



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

Utility library that provides utility classes for calculate checksums for files, byte arrays and objects

If you like this project put a ⭐ and donate

# Donations

If you like this library, please consider a donation through paypal: <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=MJ7V43GU2H386" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" border="0" />
</a>

or over bitcoin or bitcoin-cash with:

36JxRRDfRazLNqUV6NsywCw1q7TK38ukpC

or over ether with:

0x588Aa02De98B1Ef70afeDC3ec5290130a3E5e273

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
		<checksum-up.version>1.1</checksum-up.version>
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

You can first define the version in the ext section and add than the following gradle dependency to your project `build.gradle` if you want to import the core functionality of checksum-up:

```
ext {
			...
    checksumUpVersion = "1.1"
			...
}
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
