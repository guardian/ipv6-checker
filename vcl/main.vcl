sub vcl_recv {
#FASTLY recv
    error 850 "";
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
        # Setting a header to the IP converts it to a string which can then be regex matched
        if (obj.http.X-Client-Ip ~ ":") {
            synthetic {"ipv6"};
        } else {
            synthetic {"ipv4"};
        }
        return(deliver);
    }
}