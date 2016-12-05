name := "ipv6-checker"

version := "1.0"

scalaVersion in ThisBuild := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala, RiffRaffArtifact)

import com.typesafe.sbt.packager.archetypes.ServerLoader.Systemd
serverLoading in Debian := Systemd

debianPackageDependencies := Seq("openjdk-8-jre-headless")
maintainer := "Simon Hildrew <simon.hildrew@theguardian.com>"
packageSummary := "IPv6 Connectivity checker"
packageDescription := """Check whether a user has an IPv6 or IPv4 connection to the application"""

riffRaffPackageType := (packageBin in Debian).value
name in Universal := normalizedName.value
topLevelDirectory in Universal := Some(normalizedName.value)
riffRaffPackageName := name.value
riffRaffManifestProjectName := riffRaffPackageName.value
riffRaffBuildIdentifier :=  Option(System.getenv("CIRCLE_BUILD_NUM")).getOrElse("dev")
riffRaffUploadArtifactBucket := Option("riffraff-artifact")
riffRaffUploadManifestBucket := Option("riffraff-builds")
riffRaffManifestBranch := Option(System.getenv("CIRCLE_BRANCH")).getOrElse("dev")
riffRaffArtifactResources += baseDirectory.value / "vcl" / "main.vcl" -> "ip-checker/main.vcl"

javaOptions in Universal ++= Seq(
  "-Dpidfile.path=/dev/null",
  "-J-XX:MaxRAMFraction=2",
  "-J-XX:InitialRAMFraction=2",
  "-J-XX:MaxMetaspaceSize=500m",
  "-J-XX:+PrintGCDetails",
  "-J-XX:+PrintGCDateStamps",
  s"-J-Xloggc:/var/log/${packageName.value}/gc.log"
)