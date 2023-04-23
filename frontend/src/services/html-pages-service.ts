import { HtmlPage, HtmlPageMapped } from "@/types/pages/html-pages";
import axios from "axios";
import authHeader from "@/services/auth-header";
import { formatDate } from "@/types/dates/date-util";

const API_URL = "http://localhost:8080/api/v1/html/pages";

class HtmlPageServices {
  addPage(page: HtmlPage) {
    return axios.post(API_URL + "/add", page, {
      headers: authHeader(),
    });
  }
  update(page: HtmlPage) {
    return axios.post(API_URL + "/update", page, {
      headers: authHeader(),
    });
  }
  getPageByUrl(url: string): Promise<HtmlPageMapped> {
    const mappedDeviced = axios
      .get<HtmlPageMapped>(API_URL + "/" + url, {
        headers: authHeader(),
      })
      .then((response) => response.data);

    return mappedDeviced;
  }
  getAllPages(): Promise<HtmlPageMapped[]> {
    const mappedDeviced = axios
      .get<HtmlPageMapped[]>(API_URL + "/list", { headers: authHeader() })
      .then((response) => response.data)
      .then((data) => {
        for (const record of data) {
          if (record.createdAt) {
            record.createdAt = formatDate(record.createdAt);
            record.updatedAt = formatDate(record.updatedAt);
          }
        }
        return data;
      });

    return mappedDeviced;
  }
}

export default new HtmlPageServices();
