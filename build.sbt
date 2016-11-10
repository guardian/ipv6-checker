name := "ipv6-checker"

version := "1.0"

scalaVersion in ThisBuild := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala, RiffRaffArtifact)
  .settings(
    name in Universal := normalizedName.value,
    topLevelDirectory in Universal := Some(normalizedName.value),
    riffRaffPackageName := name.value,
    riffRaffManifestProjectName := riffRaffPackageName.value,
    riffRaffBuildIdentifier :=  Option(System.getenv("CIRCLE_BUILD_NUM")).getOrElse("dev"),
    riffRaffUploadArtifactBucket := Option("riffraff-artifact"),
    riffRaffUploadManifestBucket := Option("riffraff-builds"),
    riffRaffManifestBranch := Option(System.getenv("CIRCLE_BRANCH")).getOrElse("dev"),
    riffRaffPackageType := (packageZipTarball in config("universal")).value,
    riffRaffArtifactResources += riffRaffPackageType.value -> s"packages/${name.value}/${name.value}.tgz"
  )

