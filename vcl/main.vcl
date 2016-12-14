sub vcl_recv {
#FASTLY recv
    if (req.is_ipv6) {
        error 850 "ipv6";
    } else {
        error 850 "ipv4";
    }
}

sub vcl_error {
#FASTLY error
    ####################
    # Return ip mode
    ####################
    if (obj.status == 850) {
        set obj.status = 200;
        set obj.http.Content-Type = "text/plain;";
        set obj.http.X-Client-Ip = client.ip;
        synthetic obj.response;
        return(deliver);
    }
}