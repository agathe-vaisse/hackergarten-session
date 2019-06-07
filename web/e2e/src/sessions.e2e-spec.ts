import {SessionsPage} from './sessions.po';
import {browser, logging} from 'protractor';

describe('workspace-project App', () => {
  let page: SessionsPage;

  beforeEach(() => {
    page = new SessionsPage();
    page.navigateTo();
  });

  it('should display the list of sessions', () => {
    page.getDefaultWelcomeMessage().then(sessionTitle => {
      expect(sessionTitle).toEqual('No sessions found');
    });
  });
});
