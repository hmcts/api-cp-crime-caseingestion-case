package uk.gov.hmcts.cp.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.gov.hmcts.cp.openapi.api.RetrieveSubmittedInfoApi;
import uk.gov.hmcts.cp.openapi.model.SubmissionDetails;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubmissionDetailsContractTest {

    @Test
    void get_submission_should_produce_staging_civil_submission_details() throws Exception {
        final Method method = RetrieveSubmittedInfoApi.class
                .getMethod("submissionsSubmissionIdGet", UUID.class, Boolean.class);
        final RequestMapping mapping = method.getAnnotation(RequestMapping.class);

        assertArrayEquals(new String[] {"/submissions/{submissionId}"}, mapping.value());
        assertArrayEquals(new String[] {"application/vnd.stagingcivil.submission-details+json"}, mapping.produces());
    }

    @Test
    void submission_details_should_carry_all_civil_backend_fields() {
        final List<String> fields = Arrays.stream(SubmissionDetails.class.getDeclaredFields())
                .map(Field::getName)
                .toList();

        for (String expected : List.of(
                "id", "status", "type", "receivedAt", "completedAt",
                "materialWarnings", "materialErrors", "caseWarnings", "caseErrors",
                "defendantWarnings", "defendantErrors",
                "fileName", "username", "prosecutingAuthority", "summonsApplicationId")) {
            assertTrue(fields.contains(expected), "SubmissionDetails is missing field " + expected);
        }
    }

    @Test
    void submission_details_date_times_should_be_offset_date_time() throws Exception {
        assertEquals(OffsetDateTime.class, SubmissionDetails.class.getDeclaredField("receivedAt").getType());
        assertEquals(OffsetDateTime.class, SubmissionDetails.class.getDeclaredField("completedAt").getType());
    }
}
