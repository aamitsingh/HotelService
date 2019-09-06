package com.Exception;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by amitsingh on 27/06/19.
 */

@RestController
public class ErrorResolver implements ErrorController {

    private static final String ERROR_PATH = "/error";
    private static final String FORWARD_REQUEST_URI = "javax.servlet.forward.request_uri";
    private static final String FORWARD_REQUEST_QUERY = "javax.servlet.forward.query_string";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * Resolved errors thrown by Spring.
     *
     * @param request  {@link javax.servlet.http.HttpServletRequest}
     * @param response {@link javax.servlet.http.HttpServletResponse}
     * @return {@link org.springframework.http.ResponseEntity}
     *
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ResponseEntity<ErrorResponse> error(HttpServletRequest request,
                                               HttpServletResponse response) {
        final HttpStatus status = (response.getStatus() == 0)
                ? HttpStatus.INTERNAL_SERVER_ERROR
                : HttpStatus.valueOf(response.getStatus());
        final String url = getRequestUrl(request);

        String message = "Invalid city";
        return new ResponseEntity<>(new ErrorResponse(status, message, url), status);
    }

    /**
     * Extracts the full request URL from the {@link javax.servlet.http.HttpServletRequest}
     *
     * @param request {@link javax.servlet.http.HttpServletRequest}
     * @return Full request URL including URI and query string
     */
    static String getRequestUrl(HttpServletRequest request) {
        final Object forwardedUri = request.getAttribute(FORWARD_REQUEST_URI);

        /**
         * Errors on the site (404, 500) will be redirected to /error by Spring
         * So check for a forwarded URI and use that instead of showing the caller the /error URL
         * Normal exceptions should have a non-forwarded URI
         */
        if (forwardedUri == null) {
            final String uri = request.getRequestURI();
            final String query = request.getQueryString();

            return query == null ? uri : uri + "?" + query;
        }
        else {
            final Object query = request.getAttribute(FORWARD_REQUEST_QUERY);
            return query == null ? forwardedUri.toString() : forwardedUri.toString() + "?" + query.toString();
        }
    }
}
