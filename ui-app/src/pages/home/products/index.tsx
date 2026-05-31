import Card from "@mui/material/Card";
import { useGetAllProducts } from "../../../queries/ProductQueries";
import CardContent from "@mui/material/CardContent";
import AppLoader from "../../../components/Loader";

const ProductsPage = () => {
  const { data: products, isFetching } = useGetAllProducts();
  if (isFetching) {
    return <AppLoader />;
  }
  return (
    <div className="h-[500px] overflow-auto p-4">
      <div className="grid grid-cols-3 gap-4">
        {products?.map((product) => (
          <Card key={product.id}>
            <CardContent>
              <img
                src={product.imageUrl}
                alt={product.name}
                className="w-[30px] h-full object-cover float-left mr-4"
              />
              <div className="font-bold">{product.name}</div>
              <div>{product.description}</div>
              <div className="font-semibold mt-2">${product.price}</div>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  );
};

export default ProductsPage;
