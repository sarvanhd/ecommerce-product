import Paper from "@mui/material/Paper";
import { useGetUsers } from "../../../queries";
import { DataGrid, type GridColDef } from "@mui/x-data-grid";
import type { User } from "../../../models/user";
import AppLoader from "../../../components/Loader";
import AddUser from "../../../components/AddUser";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import { useState } from "react";
import IconButton from "@mui/material/IconButton";
import CloseIcon from "@mui/icons-material/Close";
import { deleteUser } from "../../../api/UserService";
import { useAlertStore } from "../../../store";
import { useQueryClient } from "@tanstack/react-query";
import { QUERY_KEYS } from "../../../queries/querykeys";
import DeleteIcon from "@mui/icons-material/Delete";
import CopyIcon from "@mui/icons-material/ContentCopy";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
import EditIcon from "@mui/icons-material/Edit";
import Container from "@mui/material/Container";

const SettingsPage = () => {
  const { data: users, isFetching } = useGetUsers();
  const [openAddUserDialog, setOpenAddUserDialog] = useState<boolean>(false);
  const [duplicateUser, setDuplicateUser] = useState<User | null>(null);
  const [editUser, setEditUser] = useState<User | null>(null);
  const paginationModel = { page: 0, pageSize: 5 };
  const { setAlertMessage } = useAlertStore();
  const queryClient = useQueryClient();

  const deleteUserById = async (id: number) => {
    await deleteUser(id);
    setAlertMessage({ message: "User deleted successfully", type: "success" });
    queryClient.invalidateQueries({ queryKey: [QUERY_KEYS.GET_ALL_USERS] });
  };
  const columns: GridColDef<User>[] = [
    { field: "id", headerName: "ID", flex: 1 },
    { field: "firstName", headerName: "First name", flex: 1 },
    { field: "lastName", headerName: "Last name", flex: 1 },
    {
      field: "email",
      headerName: "Email",
      flex: 1,
    },
    {
      field: "dob",
      headerName: "Date of birth",
      sortable: false,
      flex: 0.8,
    },
    {
      field: "delete",
      headerName: "Actions",
      width: 120,
      renderCell: (params) => (
        <>
          <IconButton
            color="inherit"
            size="small"
            onClick={() => {
              setEditUser(params.row);
              setOpenAddUserDialog(true);
            }}
          >
            <EditIcon fontSize="small" />
          </IconButton>
          <IconButton
            color="inherit"
            size="small"
            onClick={() => {
              setDuplicateUser(params.row);
              setOpenAddUserDialog(true);
            }}
          >
            <CopyIcon fontSize="small" />
          </IconButton>
          <IconButton
            color="error"
            size="small"
            onClick={() => {
              console.log(params.row);
              deleteUserById(params.row.id);
            }}
          >
            <DeleteIcon fontSize="small" />
          </IconButton>
        </>
      ),
    },
  ];
  if (isFetching) {
    return <AppLoader />;
  }
  return (
    <Container maxWidth="md">
      <IconButton
        className="float-right"
        onClick={() => setOpenAddUserDialog(true)}
      >
        <AddCircleOutlineIcon color="primary" fontSize="medium" />
      </IconButton>
      <h2 className="text-2xl font-bold mb-4 mt-4">Users</h2>
      <Paper sx={{ height: 400, width: "100%" }}>
        <DataGrid
          rows={users}
          columns={columns}
          initialState={{ pagination: { paginationModel } }}
          pageSizeOptions={[5, 10]}
          sx={{ border: 0 }}
        />
      </Paper>
      <AddUserDialog
        handleClose={() => {
          setDuplicateUser(null);
          setEditUser(null);
          setOpenAddUserDialog(false);
        }}
        isEdit={Boolean(editUser)}
        open={openAddUserDialog}
        user={editUser || duplicateUser}
      />
    </Container>
  );
};
export default SettingsPage;

const AddUserDialog = ({
  handleClose,
  open,
  user,
  isEdit,
}: {
  handleClose: () => void;
  open: boolean;
  user?: User | null;
  isEdit: boolean;
}) => {
  return (
    <Dialog onClose={handleClose} open={open}>
      <DialogTitle className="!pb-0">
        <div className="flex justify-between items-center">
          {user ? (isEdit ? "Edit User" : "Copy User") : "Add User"}
          <IconButton onClick={handleClose}>
            <CloseIcon />
          </IconButton>
        </div>
      </DialogTitle>
      <DialogContent className="mb-4">
        <AddUser isEdit={isEdit} closePopup={handleClose} userData={user} />
      </DialogContent>
    </Dialog>
  );
};
