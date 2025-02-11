# RectIntersect

**RectIntersect** is a Java project that provides a simple library for working with axis-aligned rectangles. The project includes functionality to:

- Create rectangles using two points or coordinate values.
- Check whether a rectangle contains a given point.
- Determine if two rectangles intersect.
- Compute the Euclidean distance (and its square) from a point to the nearest point on a rectangle.
- Generate a human-readable string representation of a rectangle.

This project was developed as part of a Data Structures course and is intended to illustrate basic geometric algorithms and object-oriented programming in Java.

---

## Features

- **Rectangle Construction:**  
  Create rectangles with either two `Point` objects (interpreting one as the lower-left and the other as the upper-right) or by directly specifying coordinate values.

- **Containment Test:**  
  Determine if a rectangle contains a given point.

- **Intersection Test:**  
  Efficiently check if two axis-aligned rectangles intersect using a simple overlap condition.

- **Distance Calculations:**  
  Compute the Euclidean distance (and the squared distance) from a point to the closest point on the rectangle.

- **Readable Output:**  
  Obtain a string representation of the rectangle in the format `[xmin, xmax] x [ymin, ymax]`.

---
