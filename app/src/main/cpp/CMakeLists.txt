cmake_minimum_required(VERSION 3.10.2) # Specify the CMake version
project("demoapplication")

# Add the prebuilt shared library (your .so file)
add_library(
        CarAlgoLib
        SHARED
        IMPORTED)

set_target_properties(
        CarAlgoLib
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libCarAlgo.so)

# Include your header file directory
include_directories(${CMAKE_SOURCE_DIR})

# Create a native library that will interact with Java
add_library(
        native-lib
        SHARED
        native-lib.cpp)

# Link libraries
find_library(log-lib log)

target_link_libraries(
        native-lib
        CarAlgoLib
        ${log-lib})
