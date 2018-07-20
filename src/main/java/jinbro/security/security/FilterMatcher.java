package jinbro.security.security;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FilterMatcher implements RequestMatcher {
    private RequestMatcher processingMatcher;
    private OrRequestMatcher orRequestMatcher;

    public FilterMatcher(String processingPath, List<String> skipPath) {
        processingMatcher = new AntPathRequestMatcher(processingPath);
        orRequestMatcher = new OrRequestMatcher(skipPath.stream().map(AntPathRequestMatcher::new).collect(toList()));
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return processingMatcher.matches(request) && !orRequestMatcher.matches(request);
    }
}
