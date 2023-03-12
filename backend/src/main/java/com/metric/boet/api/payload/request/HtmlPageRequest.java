package com.metric.boet.api.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class HtmlPageRequest {
        @NotBlank
        @Size(min = 3, max = 20)
        private String url;

        @NotBlank
        private String html;

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public String getHtml() {
                return html;
        }

        public void setHtml(String html) {
                this.html = html;
        }
}
