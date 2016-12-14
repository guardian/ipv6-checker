name := "ipv6-checker"

version := "1.0"

scalaVersion in ThisBuild := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(RiffRaffArtifact)

riffRaffPackageName := name.value
riffRaffManifestProjectName := name.value
riffRaffBuildIdentifier :=  Option(System.getenv("CIRCLE_BUILD_NUM")).getOrElse("dev")
riffRaffUploadArtifactBucket := Option("riffraff-artifact")
riffRaffUploadManifestBucket := Option("riffraff-builds")
riffRaffManifestBranch := Option(System.getenv("CIRCLE_BRANCH")).getOrElse("dev")
riffRaffArtifactResources := Seq(
  baseDirectory.value / "vcl" / "main.vcl" -> "ip-checker/main.vcl",
  baseDirectory.value / "riff-raff.yaml" -> "riff-raff.yaml"
)