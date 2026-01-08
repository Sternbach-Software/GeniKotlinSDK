package org.sternbach.software.genikotlinsdk

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform