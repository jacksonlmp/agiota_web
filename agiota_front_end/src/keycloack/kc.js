import configs from "./configs";
import Keycloak from 'keycloak-js';
import {api} from "../api/api";

const kc = new Keycloak(configs);

kc.init({
    onLoad: 'login-required', // check-sso ou login-required
    checkLoginIframe: true,
    pkceMethod: 'S256',
    client_id: 'agiotaApp-frontend'
}).then((auth) => {
    if (!auth) {
        window.location.reload();
    } else {
        console.info('Authentication failed');
        console.log('Auth', auth);
        console.log('Keycloack', kc);
        console.log('Access token', kc.token);
        console.log();

        api.defaults.headers.common['Authorization'] = `Bearer ${kc.token}`;
        kc.onTokenExpired = () => {
          console.log('token expired');
        };
    }
}, () => {
    console.error('Authentication failed');
});

export default kc;