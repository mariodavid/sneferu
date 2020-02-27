package de.diedavids.sneferu.environment;

import com.haulmont.cuba.client.ClientUserSession;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.config.WindowConfig;
import com.haulmont.cuba.gui.sys.UiControllersConfiguration;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.security.role.RoleDefinition;
import com.haulmont.cuba.web.app.main.MainScreen;
import com.haulmont.cuba.web.testsupport.TestContainer;
import com.haulmont.cuba.web.testsupport.TestUiEnvironment;
import com.haulmont.cuba.web.testsupport.ui.TestWindowConfig;
import de.diedavids.sneferu.CubaWebUiTestAPI;
import de.diedavids.sneferu.UiTestAPI;
import de.diedavids.sneferu.screen.StandardEditorTestAPI;
import de.diedavids.sneferu.screen.StandardLookupTestAPI;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Locale;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class SneferuTestUiEnvironment extends TestUiEnvironment implements
    ParameterResolver {

    private UiTestAPI uiTestAPI;
    private Class<? extends MainScreen> mainScreenClass;


    public SneferuTestUiEnvironment(TestContainer container) {
        super(container);
    }

    // todo temporary workaround to use full access role by default
    @Override
    protected void setupSession() {

        sessionSource = container.getBean(UserSessionSource.NAME);

        UserSession serverSession = sessionSource.createTestSession();
        ClientUserSession session = new ClientUserSession(serverSession);
        session.setAuthenticated(isSessionAuthenticated());

        sessionSource.setSession(session);
    }

    // we need to add extended screens as a separate configuration otherwise we'll get duplication exception
    @Override
    protected void exportScreens(String... packages) {
        TestWindowConfig windowConfig = container.getBean(TestWindowConfig.class);

        UiControllersConfiguration configuration = new UiControllersConfiguration();
        getInjector().autowireBean(configuration);
        configuration.setBasePackages(Arrays.asList(packages));

        UiControllersConfiguration testConf = new UiControllersConfiguration();
        getInjector().autowireBean(testConf);
        testConf.setBasePackages(Arrays.asList("com.haulmont.sample.petclinic.testscreens"));

        windowConfig.setConfigurations(Arrays.asList(configuration, testConf));
        windowConfig.reset();
    }

    @Override
    protected void before() throws Throwable {
        super.before();

        uiTestAPI = new CubaWebUiTestAPI(
            this,
            AppBeans.get(ScreenBuilders.class),
            mainScreenClass
        );
    }


    /**
     * Sets authenticated flag to the mocked user session.
     *
     * @param sessionAuthenticated true if user is authenticated
     * @return this
     */
    public SneferuTestUiEnvironment sessionAuthenticated(boolean sessionAuthenticated) {
        this.sessionAuthenticated = sessionAuthenticated;
        return this;
    }

    /**
     * Sets locale to the mocked user session.
     *
     * @param locale locale
     * @return this
     */
    public SneferuTestUiEnvironment withLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    /**
     * Sets user login to the mocked user session.
     *
     * @param userLogin user login
     * @return this
     */
    public SneferuTestUiEnvironment withUserLogin(String userLogin) {
        this.userLogin = userLogin;
        return this;
    }

    /**
     * Sets user name to the mocked user session.
     *
     * @param userName user name
     * @return this
     */
    public SneferuTestUiEnvironment withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Sets the role definition to the mocked user session.
     *
     * @param roleDefinition role definition
     * @return this
     */
    public SneferuTestUiEnvironment withRoleDefinition(RoleDefinition roleDefinition) {
        this.roleDefinition = roleDefinition;
        return this;
    }

    /**
     * Overrides screen packages that will be scanned by {@link WindowConfig}.
     *
     * @param screenPackages screen packages
     * @return this
     */
    public SneferuTestUiEnvironment withScreenPackages(String... screenPackages) {
        this.screenPackages = screenPackages;
        return this;
    }


    public SneferuTestUiEnvironment withMainScreen(Class<? extends MainScreen> mainScreenClass) {
        this.mainScreenClass = mainScreenClass;
        return this;
    }




    public UiTestAPI getUiTestAPI() {
        return uiTestAPI;
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
        ExtensionContext extensionContext) throws ParameterResolutionException {
        return isScreenParameter(parameterContext) ||
            isEntityParameter(parameterContext);
    }

    private boolean isScreenParameter(ParameterContext parameterContext) {
        return
            isStartScreenParameter(parameterContext) ||
                isSubsequentScreenParameter(parameterContext);
    }

    private boolean isSubsequentScreenParameter(ParameterContext parameterContext) {
        return parameterContext.isAnnotated(
            SubsequentScreen.class);
    }

    private boolean isStartScreenParameter(ParameterContext parameterContext) {
        return parameterContext.isAnnotated(StartScreen.class);
    }

    @Override
    public Object resolveParameter(
        ParameterContext parameterContext,
        ExtensionContext extensionContext
    ) throws ParameterResolutionException {

        if (isScreenParameter(parameterContext)) {
            return getScreenValue(parameterContext);
        }

        else if (isEntityParameter(parameterContext)) {
            return getEntityValue(parameterContext);
        }

        else {
            return cannotHandle();
        }
    }

    private Object cannotHandle() {
        throw new IllegalArgumentException("Unsupported Parameter to resolve");
    }

    private Object getEntityValue(
        ParameterContext parameterContext
    ) {
        final Parameter parameter = parameterContext.getParameter();
        final Class<?> parameterType = parameter.getType();
        final Metadata metadata = container.getBean(Metadata.class);
        return metadata.create((Class) parameterType);
    }

    private boolean isEntityParameter(ParameterContext parameterContext) {
        return parameterContext.isAnnotated(NewEntity.class);
    }


    private Object getScreenValue(
        ParameterContext parameterContext
    ) {

        if (isStartScreenParameter(parameterContext)) {
            return getStartScreenParameter(parameterContext);
        }
        else if (isSubsequentScreenParameter(parameterContext)) {
            return getSubsequentScreenParameter(parameterContext);
        }
        else {
            return cannotHandle();
        }
    }

    private Object getSubsequentScreenParameter(
        ParameterContext parameterContext
    ) {

        final Parameter parameter = parameterContext.getParameter();
        final Class<?> parameterType = parameter.getType();
        Type type = parameter.getParameterizedType();
        if (!(type instanceof ParameterizedType)) {
            return cannotHandle();
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();

        if (StandardEditorTestAPI.class.equals(parameterType)) {
            if (actualTypeArguments.length != 2) {
                return cannotHandle();
            }

            return getUiTestAPI().getLazyOpenedEditorScreen((Class) actualTypeArguments[0]);
        }

        else {
            return cannotHandle();
        }
    }

    private Object getStartScreenParameter(
        ParameterContext parameterContext
    ) {

        final Parameter parameter = parameterContext.getParameter();
        final Class<?> parameterType = parameter.getType();
        Type type = parameter.getParameterizedType();
        if (!(type instanceof ParameterizedType)) {
            return cannotHandle();
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();


        if (StandardLookupTestAPI.class.equals(parameterType)) {
            if (actualTypeArguments.length != 2) {
                return cannotHandle();
            }
            return (StandardLookupTestAPI) getUiTestAPI().openStandardLookup(
                (Class) actualTypeArguments[0],
                (Class) actualTypeArguments[1]
            );
        }
        else if (StandardEditorTestAPI.class.equals(parameterType)) {
            if (actualTypeArguments.length != 2) {
                return cannotHandle();
            }
            return (StandardEditorTestAPI) getUiTestAPI().openStandardEditor(
                (Class) actualTypeArguments[0],
                (Class) actualTypeArguments[1]
            );
        }
        else {
            return cannotHandle();
        }
    }
}