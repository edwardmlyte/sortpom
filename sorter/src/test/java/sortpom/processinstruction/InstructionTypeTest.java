package sortpom.processinstruction;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static sortpom.processinstruction.InstructionType.IGNORE;
import static sortpom.processinstruction.InstructionType.RESUME;

/**
 * @author bjorn
 * @since 2013-12-28
 */
public class InstructionTypeTest {
    @Test
    public void nextAfterIgnoreShouldBeResume() throws Exception {
        assertThat(IGNORE.next(), is(RESUME));
        assertThat(IGNORE.next().next(), is(IGNORE));
    }

    @Test
    public void nextAfterResumeShouldBeIgnore() throws Exception {
        assertThat(RESUME.next(), is(IGNORE));
        assertThat(RESUME.next().next(), is(RESUME));
    }

    @Test
    public void testContainsType() throws Exception {
        assertThat(InstructionType.containsType("ignore"), is(true));
        assertThat(InstructionType.containsType("IGNORE"), is(true));
        assertThat(InstructionType.containsType("IgNoRe"), is(true));
        assertThat(InstructionType.containsType("resume"), is(true));
        assertThat(InstructionType.containsType("RESUME"), is(true));
        assertThat(InstructionType.containsType("rEsUmE"), is(true));
        assertThat(InstructionType.containsType("token"), is(false));
        assertThat(InstructionType.containsType("gurka"), is(false));
    }

    @Test
    public void testMatches() throws Exception {
        assertThat(IGNORE.matches("ignore"), is(true));
        assertThat(IGNORE.matches("IGNORE"), is(true));
        assertThat(IGNORE.matches("IgNoRe"), is(true));
        assertThat(IGNORE.matches("resume"), is(false));

        assertThat(RESUME.matches("resume"), is(true));
        assertThat(RESUME.matches("RESUME"), is(true));
        assertThat(RESUME.matches("rEsUmE"), is(true));
        assertThat(RESUME.matches("token"), is(false));
    }
}