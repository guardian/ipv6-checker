ipv6-checker
============

A little bit of fastly VCL to determine if the caller has an IPv6 connection or not.

When any endpoint is called it will return a string - one of `ipv4` or `ipv6` depending on the incoming request.