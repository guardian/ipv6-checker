acl ipv4 {
  "0.0.0.0"/0;
}

sub vcl_recv {
#FASTLY recv
    if (client.ip ~ ipv4) {
        error 854 "";
    } else {
        error 856 "";
    }
}

sub vcl_error {
#FASTLY error
    ####################
    # Return ip mode
    ####################
    if (obj.status == 854 || obj.status == 856) {
        set obj.status = 200;
        set obj.http.Content-Type = "text/plain;";
        set obj.http.X-Client-Ip = client.ip;
        if (obj.status == 854) {
            synthetic {"ipv4"};
        } else {
            synthetic {"ipv6"};
        }
        return(deliver);
    }
}