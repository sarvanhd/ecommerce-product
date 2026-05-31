import { Navigate, type RouteObject } from "react-router-dom";
import AuthLayout from "../pages/auth";
import Login from "../pages/auth/login";
import Signup from "../pages/auth/signup";
import Home from "../pages/home";
import DashboardPage from "../pages/home/dashboard";
import ProductsPage from "../pages/home/products";
import SettingsPage from "../pages/home/settings";

export const routes: RouteObject[] = [
  {
    path: "/",
    Component: Home,
    children: [
      { index: true, element: <Navigate to="dashboard" replace /> },
      {
        path: "dashboard",
        Component: DashboardPage,
      },
      {
        path: "products",
        Component: ProductsPage,
      },
      {
        path: "settings",
        Component: SettingsPage,
      },
    ],
  },
  {
    Component: AuthLayout,
    children: [
      { path: "login", Component: Login },
      { path: "register", Component: Signup },
    ],
  },
];
