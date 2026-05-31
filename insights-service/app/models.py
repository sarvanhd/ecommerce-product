from pydantic import BaseModel


class Users(BaseModel):
    username: str
    email: str
    password: str

class InsightSchema(BaseModel):
    id: int
    title: str
    description: str

    model_config = {"from_attributes": True}