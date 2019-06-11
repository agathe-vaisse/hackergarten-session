import {browser, by, element} from 'protractor';

export class SessionsPage {

  navigateTo() {
    return browser.get(browser.baseUrl) as Promise<any>;
  }


  getDefaultWelcomeMessage() {
    return element(by.css('app-root #zero-session')).getText();
  }
}
