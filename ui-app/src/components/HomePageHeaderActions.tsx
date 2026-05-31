import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import Popover from "@mui/material/Popover";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { logout } from "../api/AuthService";
import NotificationsIcon from "@mui/icons-material/Notifications";
import LogoutIcon from "@mui/icons-material/Logout";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import useWebsocket from "../hooks/useWebsocket";
import Badge from "@mui/material/Badge";

const HomePageHeaderActions = () => {
  const [logOutEl, setLogOutEl] = useState<HTMLButtonElement | null>(null);
  const [notificationEl, setNotificationEl] =
    useState<HTMLButtonElement | null>(null);
  const navigate = useNavigate();
  const logoutUser = () => {
    logout();
    navigate("/login");
  };
  const [notifications, setNotifications] = useState<
    { type: string; msg: string; seen: boolean }[]
  >([]);
  const [latestCount, setLatestCount] = useState(0);
  const { client, connected } = useWebsocket();
  useEffect(() => {
    if (!client || !connected) return;

    const subscription = client.subscribe("/notifications/alerts", (msg) => {
      if (msg.body) {
        const newMsg = JSON.parse(msg.body);
        console.log("Received:", newMsg);
        setNotifications((prev) => [{ ...newMsg, seen: false }, ...prev]);
        setLatestCount((prev) => prev + 1);
      }
    });

    return () => {
      subscription.unsubscribe();
    };
  }, [client, connected]);

  const onNotificationClose = () => {
    setNotificationEl(null);
    setNotifications((prev) => prev.map((n) => ({ ...n, seen: true })));
  };

  return (
    <Box sx={{ marginLeft: "auto" }}>
      <Badge badgeContent={latestCount} color="error">
        <IconButton
          color="inherit"
          onClick={(event: React.MouseEvent<HTMLButtonElement>) => {
            setNotificationEl(event.currentTarget);
            setLatestCount(0);
          }}
          sx={{ padding: 0 }}
        >
          <NotificationsIcon />
        </IconButton>
      </Badge>
      <Menu
        anchorEl={notificationEl}
        open={Boolean(notificationEl)}
        onClose={onNotificationClose}
        slotProps={{
          list: {
            "aria-labelledby": "basic-button",
            sx: { paddingY: 0 },
          },
          paper: {
            sx: {
              maxHeight: 600,
              minHeight: notifications.length > 0 ? 20 : 60,
              minWidth: 300,
              bgcolor: "aliceblue",
            },
          },
        }}
      >
        {notifications &&
          notifications.map((notification, index) => (
            <MenuItem
              key={index}
              sx={{
                bgcolor: notification.seen ? "inherit" : "lightblue",
                "&:hover": {
                  bgcolor: notification.seen
                    ? "rgba(0, 0, 0, 0.04)"
                    : "lightblue",
                },
              }}
            >
              <div className="flex flex-col">
                <span className="font-bold capitalize text-xs">
                  {notification.type}:
                </span>
                <span className="text-gray-600 text-xs">
                  {notification.msg}
                </span>
              </div>
            </MenuItem>
          ))}
        {notifications.length === 0 && (
          <div className="flex flex-col h-full w-full justify-center items-center p-4">
            <span className="text-gray-600">No new notifications</span>
          </div>
        )}
      </Menu>
      <IconButton
        color="inherit"
        aria-label="logout"
        onClick={(event: React.MouseEvent<HTMLButtonElement>) => {
          setLogOutEl(event.currentTarget);
        }}
        edge="end"
        sx={{ marginLeft: "10px" }}
      >
        <LogoutIcon />
      </IconButton>
      <Popover
        open={Boolean(logOutEl)}
        anchorEl={logOutEl}
        onClose={() => {
          setLogOutEl(null);
        }}
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "left",
        }}
      >
        <Button onClick={logoutUser} className="!text-black">
          Logout
        </Button>
      </Popover>
    </Box>
  );
};

export default HomePageHeaderActions;
