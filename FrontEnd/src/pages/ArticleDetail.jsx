import { useState, useEffect } from "react"
import { useParams } from "react-router-dom";

function ArticleDetail() {
  const { articleId } = useParams();
  const [articleDetail, setArticleDetail] = useState();

  useEffect(() => {
    fetch(`http://localhost:8080/api/v1/articles/${articleId}`)
        .then((res) => res.json())
        .then((res) => {
            console.log(res.data)
            setArticleDetail(res.data)
        })
        .catch((error) => console.error("Error fetching article:", error));
  }, [])

  if (!articleDetail) {
    return <p>Loading...</p>; // Show a loading state if articleDetail is null
  }

  return (
    <div>
      <h1>Article Detail</h1>
      <p>ID: {articleDetail.id}</p>
      <p>Subject: {articleDetail.subject}</p>
      <p>Content: {articleDetail.content}</p>
      <p>Author: {articleDetail.author}</p>
    </div>
  );
}

export default ArticleDetail