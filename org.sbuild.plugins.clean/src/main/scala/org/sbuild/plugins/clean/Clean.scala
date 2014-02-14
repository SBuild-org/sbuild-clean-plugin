package org.sbuild.plugins.clean

import de.tototec.sbuild._
import java.io.File

case class Clean(
  dirs: Seq[File],
  evictCache: Boolean,
  targetName: String,
  dependsOn: TargetRefs = Seq())
