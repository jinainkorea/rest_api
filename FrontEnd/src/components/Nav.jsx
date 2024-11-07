import { Link } from 'react-router-dom'

function Nav() {
  return (<>
    <li>
        <Link to="/">홈</Link>
    </li>
    <li>
        <Link to="/auth/login">로그인</Link>
    </li>
    <li>
        <Link to="/article/list">게시글 목록</Link>
    </li>
  </>)
}

export default Nav