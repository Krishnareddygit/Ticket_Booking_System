# Concurrent Ticket Booking System (Java Multithreading)

## Overview

This code simulates a **concert ticket booking system** where multiple users attempt to book tickets **concurrently**.
The system ensures that **tickets are never oversold** even when many users try to book at the same time.

The implementation uses **Java multithreading**, **ThreadPoolExecutor**, and **AtomicInteger** to guarantee **thread-safe operations** and high concurrency performance.

---

## Problem Statement

In a real ticket booking system, multiple users may attempt to reserve tickets simultaneously.
If concurrency is not handled correctly, this can lead to **race conditions** and **overselling of tickets**.

This system solves that problem by:

* Simulating multiple users booking tickets concurrently
* Ensuring thread-safe ticket updates
* Preventing overselling using **atomic operations**

---

## Features

* Simulates **100 concurrent users**
* Each user requests **1–3 tickets randomly**
* Uses **ThreadPoolExecutor** for efficient thread management
* Ensures **thread-safe booking** using **AtomicInteger**
* Prevents **race conditions**
* Tracks **successful and failed bookings**
* Prints **final booking summary**
* Measures **execution time of the simulation**
