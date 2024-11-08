import { useState, useEffect } from "react"
import { Link } from "react-router-dom"; // Link 컴포넌트를 임포트

function ArticleList() {
  const [articleList, setAritlceList] = useState([])

  useEffect(() => {
    fetch('http://localhost:8080/api/v1/articles')
        .then((res) => res.json())
        .then((res) => {
            console.log(res.data)
            setAritlceList(res.data)
        })
  }, [])

  return (
    <>
      <ul>
        {articleList.map((article) => (
          <li key={article.id}>
            <Link to={`/article/${article.id}`}>{article.id} / {article.subject} / {article.content} / {article.author}</Link>
          </li>
        ))}
      </ul>
    </>
  )
}

export default ArticleList