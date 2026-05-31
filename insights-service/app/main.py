from fastapi import FastAPI, Depends
from sqlalchemy.orm import Session
from app import models, db_models
from app.database import get_db, engine

app = FastAPI()
db_models.Base.metadata.create_all(bind=engine)

@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}

# Get all objects
@app.get("/insights", response_model=list[models.InsightSchema])
def get_insights(db: Session = Depends(get_db)):
    return db.query(db_models.Insight).all()

# Get single object by ID
@app.get("/insights/{id}", response_model=models.InsightSchema)
def get_insight(id: int, db: Session = Depends(get_db)):
    return db.query(db_models.Insight).filter(db_models.Insight.id == id).first()