import { HtmlPage, HtmlPageMapped } from "@/types/pages/html-pages";
import {
  ApiGatewayService,
  EmptyReuqest,
  HTTP_PROTOCOLS,
  KeyRequest,
} from "./api-gateway-service";
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
    const payload: HtmlPage = page;
    return this.apiService.callApiRequest<void>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "CreateHtmlPage",
      payload,
      HTTP_PROTOCOLS.POST,
      { headers: { Authorization: authHeader() } }
    );
  }

  update(page: HtmlPage) {
    const payload: HtmlPage = page;
    return this.apiService.callApiRequest<void>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "UpdateHtmlPage",
      payload,
      HTTP_PROTOCOLS.POST,
      { headers: { Authorization: authHeader() } }
    );
  }

  viewPageByUrl(url: string): Promise<HtmlPageMapped> {
    const payload: KeyRequest = { key: url };
    return this.apiService.callApiRequest<HtmlPageMapped>(
      "com.metric.boet.api.endpoints.open",
      API_GROUP_KEY,
      "ViewHtmlPage",
      payload,
      HTTP_PROTOCOLS.GET,
      { headers: { Authorization: authHeader() } },
      true
    );
  }

  getPageByUrl(url: string): Promise<HtmlPageMapped> {
    const payload: KeyRequest = { key: url };
    return this.apiService.callApiRequest<HtmlPageMapped>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      "GetHtmlPage",
      payload,
      HTTP_PROTOCOLS.GET,
      { headers: { Authorization: authHeader() } }
    );
  }

  getAllPages(): Promise<HtmlPageMapped[]> {
    const payload: EmptyReuqest = {};
    return this.apiService
      .callApiRequest<HtmlPageMapped[]>(
        API_PACKAGE_PREFIX,
        API_GROUP_KEY,
        "ListAllHtmlPages",
        payload,
        HTTP_PROTOCOLS.GET,
        { headers: { Authorization: authHeader() } }
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
