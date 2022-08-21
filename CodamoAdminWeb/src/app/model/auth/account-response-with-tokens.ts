import {AuthorizationResponse} from './authorization-response';
import {AccountResponse} from './account-response';

export interface AccountResponseWithTokens {
  tokens: AuthorizationResponse;
  account: AccountResponse;
}
