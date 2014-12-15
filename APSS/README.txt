/*
APSS - Another Plate solving software
A free software for identifying the stars in the nightsky
Copyright (C) Stefan Babel, s-babel@web.de

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

The goal of this software to create a Java plate solving software,
as already existing with astrometry.net.

As it stands now, we only support browsing through the catalog,
but already have a concept on how to do the pattern matching
and the main classes for this task have already been written.

For convenience we are using the Jama library for solving linear
equations which arise from projecting the stars onto a plane.
Maybe in a future release we might switch to our own method
of doing this.

If you want to support this project feel free to contact me:

Stefan Babel: s.babel@web.de


TODO:
--------------------------------------
-> Threshold JPEG
-> Convert picture matrix to points matrix
-> Think of clever hash for quads
-> Think of a good definition of a neighbourhood of quads
-> Think of a clever pattern matching strategy for comparing quads 
-> Create GUI ( maybe as seperate subproject for testing )
----> Adjustable accFactor with slide-bar
----> Display quads in picture