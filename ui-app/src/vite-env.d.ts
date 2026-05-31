/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_USER_SERVICE_URL: string;
  readonly VITE_PRODUCT_SERVICE_URL: string;
  readonly VITE_AUTH_SERVICE_URL: string;
  // more env variables...
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
