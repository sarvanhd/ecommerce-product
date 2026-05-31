from sqlalchemy import Column, Integer, String
from app.database import Base
class Insight(Base):
    __tablename__ = "insights"

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String)
    description = Column(String)