import { HtmlPage, HtmlPageMapped } from "@/types/pages/html-pages";
import { ApiGatewayService, HTTP_PROTOCOLS } from "./api-gateway-service";
import authHeader from "@/services/auth-header";
import { formatDate } from "@/types/dates/date-util";

const API_PACKAGE_PREFIX = "com.metric.boet.api.endpoints.closed";
const API_GROUP_KEY = "pages";

class HtmlPageServices {
  private apiService: ApiGatewayService;

  constructor() {
    this.apiService = new ApiGatewayService();
  }

  addPage(page: HtmlPage) {
    return this.apiService.callApiRequest<void>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "CreateHtmlPage",
      page,
      HTTP_PROTOCOLS.POST,
      { headers: {Authorization: authHeader()} }, 
    );
  }

  update(page: HtmlPage) {
    return this.apiService.callApiRequest<void>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "UpdateHtmlPage",
      page,
      HTTP_PROTOCOLS.POST,
      { headers: {Authorization: authHeader()} }, 
    );
  }

  getPageByUrl(url: string): Promise<HtmlPageMapped> {
    return this.apiService.callApiRequest<HtmlPageMapped>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "GetHtmlPage",
      { url },
      HTTP_PROTOCOLS.GET,
      { headers: {Authorization: authHeader()} }, 
    );
  }

  getAllPages(): Promise<HtmlPageMapped[]> {
    return this.apiService
      .callApiRequest<HtmlPageMapped[]>(
        API_PACKAGE_PREFIX,
        API_GROUP_KEY,
        "ListAllHtmlPages",
        {},
        HTTP_PROTOCOLS.GET,
        { headers: {Authorization: authHeader()} }, 
        )
      .then((data) => {
        for (const record of data) {
          if (record.createdAt) {
            record.createdAt = formatDate(record.createdAt);
            record.updatedAt = formatDate(record.updatedAt);
          }
        }
        return data;
      });
  }
}

export default new HtmlPageServices();
