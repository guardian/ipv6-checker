ipv6-checker
============

A little bot of fastly VCL to determine if the callee has an IPv6 connection or not.

When any endpoint is called it will return a string - one of `ipv4` or `ipv6` depending on the incoming request.