export type User = {
  id: number;
  lastName: string;
  firstName: string;
  email: string;
  dob: string;
  updatedAt?: string;
  createdAt?: string;
  address: {
    id?: number;
    street?: string;
    city?: string;
    state?: string;
    postalCode?: string;
    country?: string;
  };
};
