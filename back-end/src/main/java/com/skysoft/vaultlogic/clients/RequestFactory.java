package com.skysoft.vaultlogic.clients;

import org.springframework.http.RequestEntity;

import java.net.URI;
import java.util.function.Supplier;

import static com.skysoft.vaultlogic.clients.MayaHeaders.X_NONCE_HEADER;
import static com.skysoft.vaultlogic.clients.MayaHeaders.X_TOKEN_HEADER;
import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;

public class RequestFactory {

    public static RequestEntity<Void> post(URI uri) {
        return template(uri).build();
    }

    public static RequestEntity<Void> post(Supplier<URI> uri) {
        return template(uri.get()).build();
    }

    public static RequestEntity<Void> post(String xToken, URI uri) {
        return template(xToken, uri).build();
    }

    public static RequestEntity<Void> post(String xToken, Supplier<URI> uri) {
        return template(xToken, uri.get()).build();
    }

    public static <T> RequestEntity<T> post(String xToken, URI uri, T body) {
        return template(xToken, uri).body(body);
    }

    public static <T> RequestEntity<T> post(String xToken, Supplier<URI> uri, T body) {
        return template(xToken, uri.get()).body(body);
    }

    public static <T> RequestEntity<T> post(URI uri, T body) {
        return template(uri).body(body);
    }

    public static <T> RequestEntity<T> post(Supplier<URI> uri, T body) {
        return template(uri.get()).body(body);
    }

    private static RequestEntity.BodyBuilder template(URI uri) {
        return RequestEntity.post(uri).header(X_NONCE_HEADER, valueOf(currentTimeMillis()));
    }

    private static RequestEntity.BodyBuilder template(String xToken, URI uri) {
        return RequestEntity.post(uri).header(X_TOKEN_HEADER, xToken).header(X_NONCE_HEADER, valueOf(currentTimeMillis()));
    }

}